package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Label;

public interface LabelRepository extends CrudRepository<Label, Long> {
    Label findByLabelname(String Label_Name);
}
