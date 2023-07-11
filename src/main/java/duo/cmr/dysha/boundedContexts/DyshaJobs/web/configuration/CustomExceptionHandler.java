package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler {

    // TODO: 19.05.2023 comment on this class for production
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(NoSuchElementException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("...Mdr! Cet Element nexiste pas.");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(SpelEvaluationException.class)
    public ModelAndView handleSpelEvaluationException(SpelEvaluationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("...Mdr! Cette  Adresse n'est pas disponibles.");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Du calme  c'est rien. ");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(MalformedURLException.class)
    public ModelAndView handleMalformedURLException(MalformedURLException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! cest adresse n'existe pas encore ;) ");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! cest adresse n'existe pas encore ;) ");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Je t'observe!");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Difficile a lire les Element de cette page!");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Il ya des incompatibilité dans cette page!");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Il ya des parametre manquants dans cette page!");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(LockedException.class)
    public ModelAndView handleLockedException(LockedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Sorry mais votre compte reste verouillé pour le moment");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(DisabledException.class)
    public ModelAndView handleDisabledException(DisabledException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Sorry mais votre compte reste Désactivé pour le moment");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }
    @ExceptionHandler(AccountExpiredException.class)
    public ModelAndView AccountExpiredException(AccountExpiredException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! On dirait que votre compte a expiré");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ModelAndView handleCredentialsExpiredException(CredentialsExpiredException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("... Mdr! Vous devez actualiser vos infos de login en modifiant voter passe car il a expiré");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Halt! t'as pas le droit d'y acceder ");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;

    }@ExceptionHandler(AuthenticationServiceException.class)
    public ModelAndView handleAuthenticationServiceException(AuthenticationServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Halt! t'as pas le droit d'y acceder a ce service ");
        //errorResponse.setMessage("...Mdr! Cet Element nexiste pas: " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        // Autres informations d'erreur personnalisées
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        //errorResponse.setMessage("Une erreur s'est produite : ");
        errorResponse.setMessage("Une erreur s'est produite : " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorResponse", errorResponse);

        return modelAndView;
    }
}

