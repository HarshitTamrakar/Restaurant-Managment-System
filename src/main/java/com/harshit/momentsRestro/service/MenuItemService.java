package com.harshit.momentsRestro.service;

import com.harshit.momentsRestro.entity.MenuItem;
import com.harshit.momentsRestro.exception.MenuItemNotFoundException;
import com.harshit.momentsRestro.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;

    public MenuItem createMenuItem(MenuItem item) {
        return menuItemRepository.save(item);
    }

    public MenuItem getMenuItem(long id) throws MenuItemNotFoundException {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if (item == null) throw new MenuItemNotFoundException("Menu item not found");
        return item;
    }

    public List<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem updateMenuItem(long id, MenuItem updatedItem) throws MenuItemNotFoundException {
        MenuItem item = menuItemRepository.findById(id).orElse(null);
        if (item == null) throw new MenuItemNotFoundException("Menu item not found");
        if (updatedItem.getDescription() != null) {
            item.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getName() != null) {
            item.setName(updatedItem.getName());
        }
        if ((Long) updatedItem.getRate() != null) {
            item.setRate(updatedItem.getRate());
        }
        return menuItemRepository.save(item);
    }

    public void deleteMenuItem(long id) {
        menuItemRepository.deleteById(id);
    }

}
