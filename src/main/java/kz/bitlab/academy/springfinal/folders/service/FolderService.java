package kz.bitlab.academy.springfinal.folders.service;

import kz.bitlab.academy.springfinal.folders.entity.FolderEntity;

import java.util.List;
public interface FolderService {

    String create(String name);

    String update(Long id, String name);

    List<FolderEntity> findAll();

    FolderEntity findById(Long id);

    void addCategory(Long id, Long categoryId);

    void removeCategory(Long id, Long categoryId);

    void delete(Long id);

}