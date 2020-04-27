package com.phonenumber.demo.Controller;

import com.phonenumber.demo.Service.GenerateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private GenerateList generateService;

    private static final String[][] alphaTable = new String[][]{{" "}, {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}
            , {"J", "K", "L"}, {"M", "N", "O"}, {"P", "R", "S"}, {"T", "U", "V"}, {"W", "X", "Y" },{"Z"}};

    @GetMapping("/generatelist")
    public ResponseEntity<?> getAllPerMutation(@RequestParam(name="input")String input) {
        List<String> res = new ArrayList<>();
        if (input == null || input.length() != 10) {
            return new ResponseEntity<>("Invalid Number", HttpStatus.OK);
        }
        StringBuilder sb = new StringBuilder();
        char[] array = input.toCharArray();
        helper(res, array, 0, alphaTable, sb);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private void helper(List<String> res, char[] input, int index, String[][] alphaTable, StringBuilder sb) {
        if (index == input.length) {
            res.add(sb.toString());
            return;
        }
        int curNumber = Character.getNumericValue(input[index]);
        sb.append(input[index]);
        helper(res, input, index + 1, alphaTable, sb);
        sb.deleteCharAt(sb.length() - 1);
        if (curNumber != 1 && index >= 7) {
            for (int i = 0; i <= alphaTable[curNumber].length - 1; i++) {
                sb.append(alphaTable[curNumber][i]);
                helper(res, input, index + 1, alphaTable, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }


    }


}
