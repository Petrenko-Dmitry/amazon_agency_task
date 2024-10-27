package com.example.amazon_agency_task.conroller;

import com.example.amazon_agency_task.dto.StatisticByAsinDTO;
import com.example.amazon_agency_task.dto.StatisticByDateDTO;
import com.example.amazon_agency_task.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.amazon_agency_task.dto.StatisticByDateDTO.convertStatistic;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/byDate/{startDate}/{endDate}")
    public List<StatisticByDateDTO> getByDate(@PathVariable String startDate, @PathVariable String endDate) {
        return convertStatistic(this.statisticService.getStatisticsByDate(startDate, endDate));
    }

    @GetMapping("/byDate/{date}")
    public List<StatisticByDateDTO> getByDate(@PathVariable String date) {
        return convertStatistic(this.statisticService.getStatisticsByDate(date));
    }

    @PostMapping("/byAsins")
    public List<StatisticByAsinDTO> getByAsin(@RequestBody List<String> values) {
        return StatisticByAsinDTO.convertStatistic(this.statisticService.getStatisticsByAsins(values));
    }

    @GetMapping("/aggregateStatisticByAllDates")
    public StatisticByDateDTO getAggregatedStatisticByAllDates() {
        return new StatisticByDateDTO(this.statisticService.getStatisticsByAllDates());
    }

    @GetMapping("/aggregateStatisticByAllAsins")
    public StatisticByAsinDTO getAggregatedStatisticByAllAsins() {
        return new StatisticByAsinDTO(this.statisticService.getStatisticsByAllAsins());
    }
}

