package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/worldclock")
    public String getTimeByTimeZone(Model model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        Date date = new Date();   //Get current time at local
        TimeZone local = TimeZone.getDefault();   //Get timezone by the local
        TimeZone locale = TimeZone.getTimeZone(city);   //Get timezone ny the specified city

        long localeTime = date.getTime() + (locale.getRawOffset() - local.getRawOffset());   //Calculate the current time in the specified city
        date.setTime(localeTime);  //Reset the date by localeTime

        //Set the dât sent to the view
        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "worldclock";
    }

}
