package com.example.api_plantanciones.dto;

import java.util.List;

public record LoginResponse(String userName, List<String> authorities, String token) {
}
