package com.project.api.Controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.api.Model.TeamInsight;


import com.project.api.Model.Data;
import com.project.api.Service.RoutesService;
import com.project.api.dto.SuperUserResponseDTO;
import com.project.api.dto.TopCountryDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RoutesController {
    
    @Autowired
    private RoutesService routesService;

    @GetMapping("/")
    public String healthCheck() {
        return "Servidor rodando!";
    }

   // POST que recebe a lista de pessoas e armazena
   @PostMapping("/users")
   public String receiveData(@RequestBody List<Data> data) {
        routesService.saveData(data);
       return  "Pessoas recebidas com sucesso!";
   }

   @GetMapping("/users/data")
    public List<String> listAllData() {
        return routesService.getAllData();
    }

    // Rota para listar só os nomes filtrados
    @GetMapping("/superusers")
    public ResponseEntity<SuperUserResponseDTO> listAllDataFiltred(HttpServletRequest request){
        long start = (Long) request.getAttribute("startTime");
        List<String> filteredData = routesService.getDataFiltred();
        long end = System.currentTimeMillis();

        SuperUserResponseDTO response = new SuperUserResponseDTO(
            Instant.now().toString(),
             end - start, 
             filteredData);
             return ResponseEntity.ok(response);
    }

    //Rota para listar os top 5 paises dos superusers
    @GetMapping("/top-countries")
    public ResponseEntity<List<TopCountryDTO>> listTopCountries(){
        List<TopCountryDTO> topCountries = routesService.getTopCountries();
        return ResponseEntity.ok(topCountries);
    }
    
    //Rota para listar as estatísticas por equipe com base nos membros, projetos e liderança.
   @GetMapping("/teams/insight")
    public List<TeamInsight> getTeamInsight() {
        return routesService.teamInsight();
    }
    

}
