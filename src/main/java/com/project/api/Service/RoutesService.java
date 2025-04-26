package com.project.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.api.Model.Data;
import com.project.api.Model.Project;
import com.project.api.dto.TopCountryDTO;
import com.project.api.Model.TeamInsight;


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


    public List<TeamInsight> teamInsight() {
    // Cria uma lista para armazenar os insights dos times
    List<TeamInsight> insights = new ArrayList<>();

    // Agrupa os dados por time
    Map<String, List<Data>> teamsGrouped = listData.stream()
            .collect(Collectors.groupingBy(data -> data.getTeam().getName()));

    // Para cada time, calcula os dados
    for (Map.Entry<String, List<Data>> entry : teamsGrouped.entrySet()) {
        String teamName = entry.getKey();
        List<Data> teamMembers = entry.getValue();
        
        // Conta a quantidade de usuários no time
        long totalMembers = teamMembers.size();
        
        // Conta a quantidade de líderes no time
        long leadersCount = teamMembers.stream()
                .filter(data -> data.getTeam().isLeader())
                .count();

        // Conta a quantidade de projetos completados no time
        long completedProjectsCount = teamMembers.stream()
                .flatMap(data -> data.getTeam().getProjects().stream())
                .filter(Project::isCompleted)
                .count();
        
        // Cria o TeamInsight e adiciona à lista
        TeamInsight insight = new TeamInsight(teamName, totalMembers, leadersCount, completedProjectsCount);
        insights.add(insight);
    }

    return insights;
}

    
}
