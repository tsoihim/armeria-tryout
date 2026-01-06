package io.github.tsoihim;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/devices")
    Flux<DeviceDTO> getDevices() {
        return deviceService.fetchDevices();
    }

    @GetMapping("/devices/{deviceId}")
    Mono<DeviceDTO> getDevice(@PathVariable Long deviceId) {
        return deviceService.fetchDevice(deviceId);
    }

    @PostMapping("/devices")
    Mono<DeviceDTO> createDevice(@RequestBody DeviceDTO.DeviceCreateDTO createDTO) {
        return deviceService.createDevice(createDTO);
    }

    @PutMapping("/devices/{deviceId}")
    Mono<DeviceDTO> updateDevice(@PathVariable Long deviceId,
                                 @RequestBody DeviceDTO.DeviceUpdateDTO updateDTO) {
        return deviceService.updateDevice(deviceId, updateDTO);
    }

    @DeleteMapping("/devices/{deviceId}")
    Mono<Void> deleteDevice(@PathVariable Long deviceId) {
        return deviceService.deleteDevice(deviceId);
    }

}