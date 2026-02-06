package com.twilight.dataTransferObjects.request;

import jakarta.validation.constraints.NotNull;

public record VehicleRequest(@NotNull String type) {

}
