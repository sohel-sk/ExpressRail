package com.expressrail.services;

import com.expressrail.entities.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {
    List<Train> trainList;
    ObjectMapper objectMapper =  new ObjectMapper();
    private static final String TRAIN_DB_PATH = "../localDB/trains.json";

    public TrainService() throws Exception{
        File trains = new File(TRAIN_DB_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    public Boolean validTrain(Train train, String source, String destination){
        List<String> stationOrders = train.getStations();
        int sourceIndex = stationOrders.indexOf(source.toLowerCase());
        int destinationIndex = stationOrders.indexOf(destination.toLowerCase());
        return (sourceIndex != -1) && (destinationIndex != -1) && (sourceIndex < destinationIndex);
    }

    public void addTrain(Train newTrain){
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
                .findFirst();
        if(existingTrain.isPresent()){
            updateTrain(newTrain);
        }else {
            trainList.add(newTrain);
            saveTrainListToFile(trainList);
        }
    }

    public void updateTrain(Train train){
        OptionalInt trainIndex = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(train.getTrainId()))
                .findFirst();
        if(trainIndex.isPresent()){
            trainList.set(trainIndex.getAsInt(),train);
            saveTrainListToFile(trainList);
        }else {
            addTrain(train);
        }
    }

    public void saveTrainListToFile(List<Train> trainList){
        try{
            objectMapper.writeValue(new File(TRAIN_DB_PATH),trainList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
