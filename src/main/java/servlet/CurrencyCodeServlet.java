package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Currency;
import service.CurrencyService;

import java.io.IOException;

@WebServlet("/currencies/*")
public class CurrencyCodeServlet extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currencyCode = req.getPathInfo().substring(1);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (currencyCode == null || currencyCode.length() <= 1) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("error: currence code not entered");
            return;
        }
        Currency currency;
        try {
            currency = currencyService.getCurrencyCode(currencyCode);
        } catch (RuntimeException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("error: this currency does not exist in the database");
            return;
        }
        objectMapper.writeValue(resp.getWriter(), currency);
    }
}
