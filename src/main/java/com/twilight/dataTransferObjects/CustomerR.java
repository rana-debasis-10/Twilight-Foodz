package com.twilight.dataTransferObjects;

import java.util.List;

public record CustomerR(String mobNo,String name,
                        List<AddressR> addresses) {}
