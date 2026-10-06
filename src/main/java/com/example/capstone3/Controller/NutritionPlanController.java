package com.example.capstone3.Controller;


import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.NutritionPlan;
import com.example.capstone3.Service.NutritionPlanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/nutrition-plans")
@AllArgsConstructor
public class NutritionPlanController {

    private final NutritionPlanService nutritionPlanService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllNutritionPlans() {
        return ResponseEntity.status(200).body(nutritionPlanService.getAllNutritionPlans());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getNutritionPlanById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(nutritionPlanService.getNutritionPlanById(id));
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<?> getNutritionPlanByUserId(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(nutritionPlanService.getNutritionPlanByUserId(userId));
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addNutritionPlan(@PathVariable Integer userId, @Valid @RequestBody NutritionPlan nutritionPlan) {
        nutritionPlanService.addNutritionPlan(userId, nutritionPlan);
        return ResponseEntity.status(200).body(new ApiResponse("Nutrition plan added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNutritionPlan(@PathVariable Integer id, @Valid @RequestBody NutritionPlan nutritionPlan) {
        nutritionPlanService.updateNutritionPlan(id, nutritionPlan);
        return ResponseEntity.status(200).body(new ApiResponse("Nutrition plan updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNutritionPlan(@PathVariable Integer id) {
        nutritionPlanService.deleteNutritionPlan(id);
        return ResponseEntity.status(200).body(new ApiResponse("Nutrition plan deleted successfully"));
    }
}
