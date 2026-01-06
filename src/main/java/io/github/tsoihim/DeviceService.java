package io.github.tsoihim;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceDTO> fetchDevices() {
        return deviceRepository.findAll().stream()
                .map(DeviceDTO::from).toList();
    }

    public DeviceDTO fetchDevice(Long id) {
        return deviceRepository.findById(id)
                .map(DeviceDTO::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public DeviceDTO createDevice(DeviceDTO.DeviceCreateDTO createDTO) {
        Device device = new Device();
        device.setName(createDTO.getName());
        deviceRepository.save(device);
        return DeviceDTO.from(device);
    }

    @Transactional
    public DeviceDTO updateDevice(Long id, DeviceDTO.DeviceUpdateDTO updateDTO) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        device.setName(updateDTO.getName());
        return DeviceDTO.from(device);
    }

    @Transactional
    public void deleteDevice(Long id) {
        deviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        deviceRepository.deleteById(id);
    }

}
