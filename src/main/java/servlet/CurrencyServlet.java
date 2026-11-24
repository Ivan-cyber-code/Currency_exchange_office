package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import exeption.CurrencyAlreadyExistsException;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/currencies")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Currency> currencyList = currencyService.getAllCurrencies();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(resp.getWriter(), currencyList);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Currency currencyResoult = null;
        String name = req.getParameter("name");
        String code = req.getParameter("code");
        String sign = req.getParameter("sign");
        if (StringUtil.isBlank(name) || StringUtil.isBlank(code) || StringUtil.isBlank(sign)) {
                ResponseUtils.sendError(resp, objectMapper, 400, "Error: a required filed for creating currency is missing");
            return;
        }
        Currency currency = new Currency(0, code, name, sign); // присвоить значение
        try {
            currencyResoult = currencyService.insertCurrency(currency);
        } catch (CurrencyAlreadyExistsException e) {
            ResponseUtils.sendError(resp, objectMapper, 409, "Error: this currency already exists");
            return;
        } catch (SQLException e) {
            ResponseUtils.sendError(resp, objectMapper, 500, "Error: a proplem at the database interaction level");
            return;
        }
        resp.setStatus(HttpServletResponse.SC_CREATED);
        objectMapper.writeValue(resp.getWriter(), currencyResoult);
    }
}
