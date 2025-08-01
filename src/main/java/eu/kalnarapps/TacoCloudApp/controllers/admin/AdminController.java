package eu.kalnarapps.TacoCloudApp.controllers.admin;

import eu.kalnarapps.TacoCloudApp.controllers.registration.RegistrationForm;
import eu.kalnarapps.TacoCloudApp.repositories.UserRepository;
import eu.kalnarapps.TacoCloudApp.services.OrderAdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderAdminService adminService;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminController(
            OrderAdminService adminService,
            UserRepository userRepo,
            PasswordEncoder passwordEncoder
    ) {
        this.adminService = adminService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showAdminPage() {
        return "admin";
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder, true));
        return "redirect:/login";
    }
}
