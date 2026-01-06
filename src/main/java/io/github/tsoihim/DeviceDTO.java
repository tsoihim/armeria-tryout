package io.github.tsoihim;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {

    private Long id;

    private String name;

    public static DeviceDTO from(Device device) {
        return new DeviceDTO(device.getId(), device.getName());
    }

    @Data
    public static class DeviceCreateDTO {

        private String name;

    }

    @Data
    public static class DeviceUpdateDTO {

        private String name;

    }
}
