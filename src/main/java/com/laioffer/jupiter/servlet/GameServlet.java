package com.laioffer.jupiter.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.laioffer.jupiter.external.TwitchClient;
import com.laioffer.jupiter.external.TwitchException;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import com.laioffer.jupiter.entity.Game;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameName = request.getParameter("game_name");
        TwitchClient client = new TwitchClient();
        response.setContentType("application/json;charset=UTF-8");
        try {
            if (gameName != null) {
                response.getWriter().print(
                        new ObjectMapper().writeValueAsString(
                                client.searchGame(gameName)
                        )
                );
            }
            else {
                response.getWriter().print(
                        new ObjectMapper().writeValueAsString(
                                client.topGames(10))
                );
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }

    }


}
