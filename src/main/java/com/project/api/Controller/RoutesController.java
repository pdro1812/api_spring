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
import com.project.api.dto.TopCountriesResponseDTO;
import com.project.api.dto.TopCountryDTO;
import com.project.api.dto.UserResponse;

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
    public ResponseEntity<UserResponse> receiveData(@RequestBody List<Data> data) {
        routesService.saveData(data);
        int quantidade = data.size();
        UserResponse response = new UserResponse("Arquivo recebido com sucesso", quantidade);
        return ResponseEntity.ok(response);
    }
   

   @GetMapping("/users/data")
   public List<Data> listAllData() {
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
    public ResponseEntity<TopCountriesResponseDTO> listTopCountries(HttpServletRequest request){
        long start = System.currentTimeMillis();  // Marca o início da execução
        List<TopCountryDTO> topCountries = routesService.getTopCountries();
        long end = System.currentTimeMillis();  // Marca o fim da execução

        TopCountriesResponseDTO response = new TopCountriesResponseDTO(
            Instant.now().toString(),  // Timestamp atual
            end - start,               // Tempo de execução
            topCountries
        );

        return ResponseEntity.ok(response);
    }

    
    //Rota para listar as estatísticas por equipe com base nos membros, projetos e liderança.
   @GetMapping("/teams/insight")
    public List<TeamInsight> getTeamInsight() {
        return routesService.teamInsight();
    }
    

}
