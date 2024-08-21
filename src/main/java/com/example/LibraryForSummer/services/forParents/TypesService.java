package com.example.LibraryForSummer.services.forParents;

import com.example.LibraryForSummer.models.Parents.Type;
import com.example.LibraryForSummer.repositories.forParents.TypesRepository;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TypesService {
    private final TypesRepository typesRepository;

    @Autowired
    public TypesService(TypesRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    public List<Type> findAll() {
        return typesRepository.findAll();
    }

    public Type findById(int id) {
        return typesRepository.findById(id).get();
    }

    @Transactional
    public void save(Type type) {
        typesRepository.save(type);
    }

    @Transactional
    public void update(int id, Type type) {
        type.setTypeId(id);
        typesRepository.save(type);
    }

    @Transactional
    public void delete(int id) {
        typesRepository.deleteById(id);
    }

    public Optional<Type> findByType(String type) {
        return typesRepository.findByType(type);
    }
}
