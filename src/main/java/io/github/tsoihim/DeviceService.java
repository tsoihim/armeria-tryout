package io.github.tsoihim;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Flux<DeviceDTO> fetchDevices() {
        return deviceRepository.findAll()
                .map(v -> objectMapper.convertValue(v, DeviceDTO.class));
    }

    public Mono<DeviceDTO> fetchDevice(Long id) {
        return deviceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(v -> objectMapper.convertValue(v, DeviceDTO.class));
    }

    public Mono<DeviceDTO> createDevice(DeviceDTO.DeviceCreateDTO createDTO) {
        return deviceRepository.save(new Device(null, createDTO.getName()))
                .map(v -> objectMapper.convertValue(v, DeviceDTO.class));
    }

    // 변환 로직이 Mono, Flux를 포함하면 flatMap, 그렇지 않다면 map
    public Mono<DeviceDTO> updateDevice(Long id, DeviceDTO.DeviceUpdateDTO updateDTO) {
        return deviceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(v -> deviceRepository.save(new Device(id, updateDTO.getName())))
                .map(v -> objectMapper.convertValue(v, DeviceDTO.class));
    }

    public Mono<Void> deleteDevice(Long id) {
        return deviceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(v -> deviceRepository.deleteById(id));
    }

}
