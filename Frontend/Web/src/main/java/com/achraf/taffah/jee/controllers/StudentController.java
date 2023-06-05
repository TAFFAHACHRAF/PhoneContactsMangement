package com.achraf.taffah.jee.controllers;

import com.achraf.taffah.jee.entities.Student;
import com.achraf.taffah.jee.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentRepository studentRepository;

    @GetMapping(path = "/students")
    public String students(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        Page<Student> list = studentRepository.findByFirstNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("students", list.getContent());
        int[] pages;
        if (list.getTotalPages() > 5) {
            pages = new int[5];
            if (page <= 3) {
                for (int i = 0; i < 5; i++)
                    pages[i] = i;
            } else {
                int j = 0;
                if (page >= (list.getTotalPages() - 2)) {
                    for (int i = list.getTotalPages() - 5; i < list.getTotalPages(); i++)
                        pages[j++] = i;
                } else {
                    for (int i = page - 2; i < (page + 3); i++)
                        pages[j++] = i;
                }
            }
        } else {
            pages = new int[list.getTotalPages()];
            for (int i = 0; i < list.getTotalPages(); i++)
                pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("maxPages", list.getTotalPages());
        return "students-list";
    }

    @DeleteMapping(path = "/delete")
    public String delete(@RequestParam Long id, @RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size) {
        studentRepository.deleteById(id);
        return "redirect:/students?page=" + page + "&keyword=" + keyword + "&size=" + size;
    }

    @GetMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(path = "/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Student student, BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size) {
        if (bindingResult.hasErrors()) return "add-student";
        studentRepository.save(student);
        return "redirect:/students?page=" + page + "&keyword=" + keyword + "&size=" + size;
    }

    @PostMapping(path = "/put")
    public String put(Model model, @Valid Student student, BindingResult bindingResult,
                      @RequestParam(defaultValue = "") String keyword,
                      @RequestParam(defaultValue = "0") int page,
                      @RequestParam(defaultValue = "5") int size) {
        if (bindingResult.hasErrors()) return "edit-student";
        studentRepository.save(student);
        return "redirect:/students?page=" + page + "&keyword=" + keyword + "&size=" + size;
    }

    @GetMapping(path = "/edit")
    public String editStudent(Model model, Long id, String keyword, int page, int size) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new RuntimeException("Student does not exist !");
        model.addAttribute("student", student);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "edit-student";
    }
}
