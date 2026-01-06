package io.github.tsoihim;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/devices")
    List<DeviceDTO> getDevices() {
        return deviceService.fetchDevices();
    }

    @GetMapping("/devices/{deviceId}")
    DeviceDTO getDevice(@PathVariable Long deviceId) {
        return deviceService.fetchDevice(deviceId);
    }

    @PostMapping("/devices")
    DeviceDTO createDevice(@RequestBody DeviceDTO.DeviceCreateDTO createDTO) {
        return deviceService.createDevice(createDTO);
    }

    @PutMapping("/devices/{deviceId}")
    DeviceDTO updateDevice(@PathVariable Long deviceId,
                                 @RequestBody DeviceDTO.DeviceUpdateDTO updateDTO) {
        return deviceService.updateDevice(deviceId, updateDTO);
    }

    @DeleteMapping("/devices/{deviceId}")
    void deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }

}