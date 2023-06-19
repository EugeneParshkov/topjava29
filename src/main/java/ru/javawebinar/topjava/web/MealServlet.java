package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.*;

public class MealServlet extends HttpServlet {

    private final static Logger log = getLogger(MealServlet.class);

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_USER = "/listMeals.jsp";
    private MealDao dao;

    public MealServlet() {
        super();
        this.dao = new MealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
//        List<MealTo> mealsTo = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 59), MealsUtil.CALORIESPERDAY);
//        req.setAttribute("listMeals", mealsTo);
//        req.getRequestDispatcher("meals.jsp").forward(req, resp);

        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            forward = LIST_USER;
            request.setAttribute("listMeals", dao.getAllMeals());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            request.setAttribute("listMeals", dao.getAllMeals());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        log.debug("doPost");

        LocalDateTime dob = dob = LocalDateTime.from(DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("dob")));
        String description = (request.getParameter("firstName"));
        String calories = (request.getParameter("lastName"));
        Meal meal = new Meal(dob, description, Integer.parseInt(calories));
        String userid = request.getParameter("userid");
        if (userid == null || userid.isEmpty()) {
            dao.addMeal(meal);
        } else {
            meal.setMealId(Integer.parseInt(userid));
            dao.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("listMeals", dao.getAllMeals());
        view.forward(request, response);
    }
}

