package eu.kalnarapps.TacoCloudApp.controllers.registration;

import eu.kalnarapps.TacoCloudApp.repositories.UserRepository;
import eu.kalnarapps.TacoCloudApp.spring.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public String registerForm(HttpServletRequest request) {
    String clientIp = IpUtils.getClientIpAddress(request);
    System.out.println("Client IP address: " + clientIp);
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(passwordEncoder, false));
    return "redirect:/login";
  }

}