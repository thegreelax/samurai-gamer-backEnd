package fr.isima.etudecaswebmobile.entities.mapper;

public interface Mapper<M, E> {
    M toModel(E entity);
    E fromModel(M model);
}