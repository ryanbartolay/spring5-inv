package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Settings;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, String> {

}
