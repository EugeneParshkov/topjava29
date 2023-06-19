package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDao {

    AtomicInteger id = new AtomicInteger(0);

    private final static Logger log = getLogger(MealDao.class);

    private List<MealTo> mealToList;

    public MealDao() {
        this.mealToList = new CopyOnWriteArrayList<>();
    }

    public void addMeal(Meal meal) {
        log.debug("addMeal");
        MealsUtil.meals.add(new Meal(id.incrementAndGet(), meal.getDateTime(),
                meal.getDescription(), meal.getCalories()));
        log.debug("meal added" + meal.toString());
    }

    public void deleteMeal(int mealId) {
        log.debug("deleteMealById");
        MealsUtil.meals.removeIf(meal -> meal.getMealId() == mealId);
        log.debug("Meal deleted" + " " + mealId);
    }

    public void updateMeal(Meal meal) {
        log.debug("updateMeal");
        for (Meal meals : MealsUtil.meals) {
            if (meals.getMealId() == meal.getMealId()) {
                deleteMeal(meals.getMealId());
                addMeal(meal);
            }
        }
        log.debug("Meal updated" + meal.toString());
    }

    public List<MealTo> getAllMeals() {
        log.debug("getAllMeals");
        return MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 59), MealsUtil.CALORIESPERDAY);
    }

    public Meal getMealById(int mealId) {
        log.debug("getMealById");
        for (Meal meal : MealsUtil.meals)
            if (meal.getMealId() == mealId)
                return meal;

            return null;
    }
}
