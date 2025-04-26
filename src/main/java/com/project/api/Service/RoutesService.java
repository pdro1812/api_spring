package com.project.api.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.api.Model.Data;
import com.project.api.Model.Projeto;
import com.project.api.dto.TopCountryDTO;


@Service
public class RoutesService {
    
    private List<Data> listData;

    // Salva os dados recebidos (pode vir do POST)
    public void saveData(List<Data> data) {
        this.listData = data;
    }

    // Retorna todos os nomes
    public List<Data> getAllData() {
        return listData;
    }

    //retorna os supersusers score >=900 and ativo
    public List<Data> getFiltredSuperUsers(){
        return listData.stream()
        .filter(p ->p.getScore() >= 900 && p.isActive())
        .collect(Collectors.toList());
    }

    //retorna o nome dos superUsers
    public List<String> getDataFiltred() {
        return getFiltredSuperUsers().stream()
        .map(Data::getName)
        .collect(Collectors.toList());
    }

    //Retorna os 5 paises com maior numero de supersuarios
    public List<TopCountryDTO> getTopCountries(){
        return getFiltredSuperUsers().stream()
            .collect(Collectors.groupingBy(Data::getCountry, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((a,b)-> Long.compare(b.getValue(), a.getValue()))
            .limit(5)
            .map(entry->new TopCountryDTO(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    // Método para obter os insights da equipe
    public Map<String, Object> getTeamInsights() {
        long startTime = System.currentTimeMillis();  // Marca o tempo de início

        // Total de membros por equipe
        Map<String, Long> totalMembros = listData.stream()
            .collect(Collectors.groupingBy(
                p -> p.getEquipe().getNome(),
                Collectors.counting()
            ));

        // Líderes por equipe
        Map<String, Long> totalLideres = listData.stream()
            .filter(p -> p.getEquipe().isLider() == true)
            .collect(Collectors.groupingBy(
                p -> p.getEquipe().getNome(),
                Collectors.counting()
            ));

        // Projetos concluídos e total de projetos por equipe
        Map<String, Map<String, Object>> projetosPorEquipe = listData.stream()
            .collect(Collectors.groupingBy(
                p -> p.getEquipe().getNome(),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    participantes -> {
                        long totalProjetos = participantes.stream()
                            .flatMap(p -> p.getEquipe().getProjetos().stream())
                            .count();

                        long projetosConcluidos = participantes.stream()
                            .flatMap(p -> p.getEquipe().getProjetos().stream())
                            .filter(projeto -> projeto.isConcluido())
                            .count();

                        double porcentagemConcluidos = totalProjetos > 0 ?
                            (double) projetosConcluidos / totalProjetos * 100 : 0.0;

                        return Map.of(
                            "totalProjetos", totalProjetos,
                            "projetosConcluidos", projetosConcluidos,
                            "porcentagemConcluidos", porcentagemConcluidos
                        );
                    }
                )
            ));


            Map<String, Object> teamData = listData.stream()
            .map(p -> p.getEquipe().getNome())
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toMap(
                equipe -> equipe,
                equipe -> {
                    long totalMembrosEquipe = totalMembros.getOrDefault(equipe, 0L);
                    long totalLideresEquipe = totalLideres.getOrDefault(equipe, 0L);

                    Map<String, Object> projetosInfo = projetosPorEquipe.getOrDefault(equipe, Map.of());

                    return Map.of(
                        "team", equipe,
                        "total_members", totalMembrosEquipe,
                        "leaders", totalLideresEquipe,
                        "completed_projects", projetosInfo.getOrDefault("projetosConcluidos", 0L),
                        "active_percentage", projetosInfo.getOrDefault("porcentagemConcluidos", 0.0)
                    );
                }
            ));

        long endTime = System.currentTimeMillis();  
        long executionTime = endTime - startTime;  

        return Map.of(
            "timestamp", Instant.now().toString(), 
            "execution_time_ms", executionTime,     
            "teams", teamData                       
        );
    }

    
}
