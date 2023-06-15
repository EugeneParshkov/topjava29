package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.*;

public class MealServlet extends HttpServlet {

    private final static Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> mealsTo = MealsUtil.createMealTo(MealsUtil.meals, MealsUtil.CALORIESPERDAY);
        req.setAttribute("listMeals", mealsTo);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
