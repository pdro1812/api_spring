package com.project.api.Controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
        long start = System.currentTimeMillis();  
        List<TopCountryDTO> topCountries = routesService.getTopCountries();
        long end = System.currentTimeMillis();  

        TopCountriesResponseDTO response = new TopCountriesResponseDTO(
            Instant.now().toString(), 
            end - start,              
            topCountries
        );

        return ResponseEntity.ok(response);
    }

    
    //rota nome das equipes
    @GetMapping("/equipes")
    public ResponseEntity<List<String>> getNomesDasEquipes() {
        long start = System.currentTimeMillis();  
        List<String> nomesDasEquipes = routesService.getNomesDasEquipes(); // Chamando o serviço para pegar os nomes das equipes
        long end = System.currentTimeMillis();
    
        return ResponseEntity.ok()
                .header("X-Response-Time", (end - start) + "ms") // Adicionando header opcional com tempo de resposta
                .body(nomesDasEquipes);
    }

     // Rota para listar o total de participantes por equipe
    @GetMapping("/equipes/participantes")
    public ResponseEntity<Map<String, Long>> getParticipantesPorEquipe() {
        Map<String, Long> participantesPorEquipe = routesService.getParticipantesPorEquipe();
        return ResponseEntity.ok(participantesPorEquipe);
    }

     // Rota para listar o total de líderes por equipe
     @GetMapping("/equipes/lideres")
     public ResponseEntity<Map<String, Long>> getLideresPorEquipe() {
         Map<String, Long> lideresPorEquipe = routesService.getLideresPorEquipe();
         return ResponseEntity.ok(lideresPorEquipe);
     }

     // Rota para listar os projetos, total de projetos, projetos concluídos e porcentagem concluída de cada equipe
     @GetMapping("/equipes/projetos")
     public ResponseEntity<Map<String, Map<String, Object>>> getProjetosPorEquipe() {
         Map<String, Map<String, Object>> projetosPorEquipe = routesService.getProjetosPorEquipe();
         return ResponseEntity.ok(projetosPorEquipe);
     }
   


    
    

}
