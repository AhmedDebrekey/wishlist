package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishRepository;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class WishlistController {

    private final WishlistRepository wishlistRepository;
    private final WishRepository wishRepository;

    public WishlistController(WishlistRepository wishlistRepository, WishRepository wishRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishRepository = wishRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("wishlist", new Wishlist());
        model.addAttribute("wishlists", wishlistRepository.findAll());
        return "index";
    }

    @PostMapping("/wishlist/create")
    public String createWishlist(@ModelAttribute Wishlist wishlist) {
        wishlistRepository.save(wishlist);
        return "redirect:/";
    }

    @PostMapping("/wishlist/{id}/delete")
        public String deleteWishlist(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {

        if (!wishlistRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Wishlist not found");
            return "redirect:/";
        }

        wishlistRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage", "Wishlist deleted successfully");
        return "redirect:/";
    }

    @GetMapping("/wishlist/{id}")
    public String showWishlist(@PathVariable Long id, Model model) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);

        if (optionalWishlist.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("wishlist", optionalWishlist.get());
        model.addAttribute("wish", new Wish());
        return "wishlist";
    }

    @PostMapping("/wishlist/{id}/wish/create")
    public String createWish(@PathVariable Long id,
                             @ModelAttribute Wish wish,
                             RedirectAttributes redirectAttributes) {

        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);

        if (optionalWishlist.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Wishlist not found");
            return "redirect:/";
        }

        Wishlist wishlist = optionalWishlist.get();
        wish.setWishlist(wishlist);
        wishRepository.save(wish);

        return "redirect:/wishlist/" + id;
    }

    @PostMapping("/wishlist/{wishlistId}/wish/{wishId}/delete")
    public String deleteWish(@PathVariable Long wishlistId,
                             @PathVariable Long wishId,
                             RedirectAttributes redirectAttributes) {

        if (!wishRepository.existsById(wishId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Wish not found");
            return "redirect:/wishlist/" + wishlistId;
        }

        wishRepository.deleteById(wishId);
        return "redirect:/wishlist/" + wishlistId;
    }

    @PostMapping("/wishlist/{wishlistId}/wish/{wishId}/reserve")
    public String toggleReserved(@PathVariable Long wishlistId,
                                 @PathVariable Long wishId) {

        wishRepository.findById(wishId).ifPresent(wish -> {
            wish.setReserved(!wish.isReserved());
            wishRepository.save(wish);
        });

        return "redirect:/wishlist/" + wishlistId;
    }
}