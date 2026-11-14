package com.tecnm.merida.market_backend_v2.web.controller;

import com.tecnm.merida.market_backend_v2.domain.Category;
import com.tecnm.merida.market_backend_v2.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "Manage product categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @Operation(summary = "Get all categories", description = "Returns a list of all product categories")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of categories")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID", description = "Returns a category by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Category found")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Category> getCategory(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new category",
            description = "Registers a new category and returns it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "category": "Lácteos",
                                                "active": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid category data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing category",
            description = "Updates the category with the given ID and returns the updated category",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "category": "Lácteos y Derivados",
                                                "active": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "400", description = "Invalid category data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Category> update(
            @Parameter(description = "Category ID to update", example = "1", required = true)
            @PathVariable("id") int categoryId,
            @RequestBody Category category) {
        return categoryService.update(categoryId, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category by ID", description = "Deletes a category by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Category ID to delete", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        if (categoryService.delete(categoryId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
