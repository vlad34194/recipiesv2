package ro.siit.recipiesv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.recipiesv2.model.Category;
import ro.siit.recipiesv2.model.Recipies;
import ro.siit.recipiesv2.model.RecipiesRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Controller
@RequestMapping("recipies")
public class RecipiesController {
    @Autowired
    private RecipiesRepository repo;

    @GetMapping
    public ModelAndView getRecipies() {
        ModelAndView modelAndView = new ModelAndView("recipies-table");
        List<Recipies> recipiesList = repo.findAll();

        modelAndView.addObject("recipieAdd", recipiesList);

        return modelAndView;
    }

//@GetMapping
//public String showHome(Model model,@RequestParam(defaultValue = "0") int page){
//    model.addAttribute("data",repo.findAll(PageRequest.of(page,6)));
//    model.addAttribute("currentPage",page);
//    return "recipies-table";
//}


    @GetMapping(path = "add")
    public ModelAndView recepiesAddForm() {
        Recipies recipies = new Recipies();
        ModelAndView modelAndView = new ModelAndView("add-form");
        recipies.setDateCreated(LocalDate.now());
        modelAndView.addObject("todayDate", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        modelAndView.addObject("recipieAdd", new Recipies());
        return modelAndView;
    }

    @PostMapping(path = "add")
    public String addRecipies(@ModelAttribute(name = "recipieAdd") @Valid Recipies recipies, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-form";
        }
        recipies.setDateCreated(LocalDate.now());
        repo.save(recipies);
        return "redirect:/recipies";

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView getEditRecipie(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-form");
        Recipies recipies = repo.findById(id).get();
        recipies.setDateCreated(recipies.getDateCreated());
        recipies.setLastModified(LocalDate.now());
        modelAndView.addObject("recipieEdit", recipies);
        modelAndView.addObject("updatedDate", LocalDate.now());
        return modelAndView;
    }

    @PostMapping(path = "/{id}/edit")
    public String updateRecepie(@ModelAttribute("recipieEdit") @Valid Recipies recipies, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-form";
        }
        recipies.setLastModified(LocalDate.now());
        repo.save(recipies);
        return "redirect:/recipies/{id}/view";
    }


    @PostMapping(path = "/{id}/view")
    public String deleteRecipieById(@RequestParam("idReteta") Long id) {
        Recipies recipies = repo.findById(id).get();
        repo.deleteById(id);
        return "redirect:/recipies";
    }

    @GetMapping(path = "about-me")
    public ModelAndView aboutMe() {
        ModelAndView modelAndView = new ModelAndView("about-me");
        return modelAndView;
    }

    @GetMapping(path = "soup")
    public ModelAndView getRecipiesSoup() {
        ModelAndView modelAndView = new ModelAndView("recipies-table");

        List<Recipies> recipiesList = repo.findAllByCategoryOrderByLastModified(Category.SOUP);
        modelAndView.addObject("recipieAdd", recipiesList);
        return modelAndView;
    }

    @GetMapping(path = "dessert")
    public ModelAndView getRecipiesDessert() {
        ModelAndView modelAndView = new ModelAndView("recipies-table");

        List<Recipies> recipiesList = repo.findAllByCategoryOrderByLastModified(Category.DESSERT);
        modelAndView.addObject("recipieAdd", recipiesList);
        return modelAndView;
    }

    @GetMapping(path = "misc")
    public ModelAndView getRecipiesMiscellaneous() {
        ModelAndView modelAndView = new ModelAndView("recipies-table");

        List<Recipies> recipiesList = repo.findAllByCategoryOrderByLastModified(Category.MISCELLANEOUS);
        modelAndView.addObject("recipieAdd", recipiesList);
        return modelAndView;
    }

    @GetMapping(path = "mainDish")
    public ModelAndView getRecipiesMainDish() {
        ModelAndView modelAndView = new ModelAndView("recipies-table");

        List<Recipies> recipiesList = repo.findAllByCategoryOrderByLastModified(Category.MAINDISH);
        modelAndView.addObject("recipieAdd", recipiesList);
        return modelAndView;
    }

    @GetMapping(path = "salad")
    public ModelAndView getRecipiesSalad() {
        ModelAndView modelAndView = new ModelAndView("recipies-table");

        List<Recipies> recipiesList = repo.findAllByCategoryOrderByLastModified(Category.SALAD);
        modelAndView.addObject("recipieAdd", recipiesList);
        return modelAndView;
    }

    @GetMapping(path = "/{id}/view")
    public ModelAndView viewRecipie(@PathVariable Long id) {
        Recipies recipies = repo.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("view-form");
        modelAndView.addObject("recipieView", recipies.getLastModified());
        modelAndView.addObject("recipieView", recipies);

        return modelAndView;
    }
}
