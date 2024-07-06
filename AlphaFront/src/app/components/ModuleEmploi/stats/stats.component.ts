import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { OfferStatistics } from 'src/app/models/OfferStatistics';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  totalApplications!: number;
  popularOffers!: OfferStatistics[];
  candidateStatistics: any;

  // Chart data
  offerIds: string[] = [];
  applicationCounts: number[] = [];

  constructor(private dashboardService: OfferService) {
    // Register all necessary components
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.loadDashboardData();
  }

  loadDashboardData(): void {
    this.dashboardService.getTotalApplications().subscribe(data => {
      this.totalApplications = data;
      console.log("Total Application : ",data);
    });

    this.dashboardService.getPopularOffers().subscribe(data => {
      this.popularOffers = data;
      console.log("Popular Offers : ",data);
      this.offerIds = this.popularOffers.map(offer => offer.company);
      this.applicationCounts = this.popularOffers.map(offer => offer.applicationCount);
      this.RenderChart();
      //this.updateChartData();
      //this.RenderChart();
    });

    this.dashboardService.getCandidateStatistics().subscribe(data => {
      console.log("Candidat Stats : ",data);
      this.candidateStatistics = data;
    });
  }

  updateChartData(): void {
    this.offerIds = this.popularOffers.map(offer => offer.company);
    this.applicationCounts = this.popularOffers.map(offer => offer.applicationCount);
  }

  RenderChart(){
    new Chart("candidacyChart", {
      type: 'bar',
      data: {
        labels: this.offerIds,
        datasets: [{
          label: 'Number of Applications per Offer',
          data: this.applicationCounts,
          backgroundColor: 'rgba(54,162,235,0.2)',
          borderColor: 'rgba(54,162,235,1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}
