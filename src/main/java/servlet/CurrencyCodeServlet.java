package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exeption.CurrencyNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Currency;
import service.CurrencyService;
import util.ResponseUtils;
import util.StringUtil;

import java.io.IOException;

@WebServlet("/currencies/*")
public class CurrencyCodeServlet extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currencyCode = req.getPathInfo().substring(1);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (StringUtil.isBlank(currencyCode)) {
            ResponseUtils.sendError(resp,objectMapper,400,"error: currence code not entered");// использовать ResponseUtil
            return;
        }
        Currency currency;
        try {
            currency = currencyService.getCurrencyCode(currencyCode);
        } catch (CurrencyNotFoundException e) {
            ResponseUtils.sendError(resp,objectMapper,404,"error: this currency does not exist in the database");
            return;
        }
        objectMapper.writeValue(resp.getWriter(), currency);
    }
}
