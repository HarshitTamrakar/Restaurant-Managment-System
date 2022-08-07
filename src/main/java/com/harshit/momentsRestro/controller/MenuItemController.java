package com.harshit.momentsRestro.controller;

import com.harshit.momentsRestro.entity.MenuItem;
import com.harshit.momentsRestro.exception.MenuItemNotFoundException;
import com.harshit.momentsRestro.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-item")
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.createMenuItem(menuItem);
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItem(@PathVariable("id") long id) throws MenuItemNotFoundException {
        return menuItemService.getMenuItem(id);
    }

    @GetMapping
    public List<MenuItem> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable("id") long id, @RequestBody MenuItem updatedItem) throws MenuItemNotFoundException {
        return menuItemService.updateMenuItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable("id") long id){
        menuItemService.deleteMenuItem(id);
    }
}
