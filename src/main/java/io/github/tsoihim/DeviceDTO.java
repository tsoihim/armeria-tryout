package io.github.tsoihim;

import lombok.Data;

@Data
public class DeviceDTO {

    private Long id;

    private String name;

    @Data
    public static class DeviceCreateDTO {

        private String name;

    }

    @Data
    public static class DeviceUpdateDTO {

        private String name;

    }
}
