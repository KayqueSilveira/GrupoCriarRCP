package com.grupo.criar.RCP.Controller;

import com.grupo.criar.RCP.Models.Dto.Position;
import com.grupo.criar.RCP.Service.impl.PilotServiceImpl;
import com.grupo.criar.RCP.Service.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("pilot")
public class PositionController {
    @Autowired
    private PilotServiceImpl pilotService;
    @Autowired
    private PositionServiceImpl positionService;
    @GetMapping("/position")
    public List<Position> getPosition () {
        var listPilot = pilotService.findAll();
        return positionService.PilotPosition(listPilot);
    }
}
