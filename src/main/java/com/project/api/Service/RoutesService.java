package com.project.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

   public List<String> getNomesDasEquipes(){
    return listData.stream()
        .map(p -> p.getEquipe().getNome())
        .filter(Objects::nonNull)
        .distinct()
        .collect(Collectors.toList());
}

    public Map<String, Long> getParticipantesPorEquipe() {
        return listData.stream()
            .collect(Collectors.groupingBy(
                p -> p.getEquipe().getNome(),  // Agrupar pelo nome da equipe
                Collectors.counting()          // Contar o número de participantes em cada equipe
            ));
    }

    // Método para contar líderes por equipe
    // Método para contar líderes por equipe
    public Map<String, Long> getLideresPorEquipe() {
        return listData.stream()
            .filter(p -> p.getEquipe().isLider() == true)  // Filtra apenas as pessoas com "lider" = true
            .collect(Collectors.groupingBy(
                p -> p.getEquipe().getNome(),  // Agrupar pelo nome da equipe
                Collectors.counting()          // Contar o número de líderes em cada equipe
            ));
    }



    
}
