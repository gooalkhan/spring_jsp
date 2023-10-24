package com.example.spring_jsp.worldcup;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorldCupServiceImpl implements WorldCupService {
    private final WorldCupMapper worldCupMapper;
    
    
}
