import React from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  Filler
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  Filler
);

const RevenueChart = ({ data = [] }) => {
  const months = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
  ];

  // Process data for chart
  const processedData = months.map((month, index) => {
    const monthData = data.find(item => item.month === index + 1);
    return {
      month,
      revenue: monthData?.revenue || 0,
      expenses: monthData?.expenses || 0,
      profit: (monthData?.revenue || 0) - (monthData?.expenses || 0)
    };
  });

  const chartData = {
    labels: months,
    datasets: [
      {
        label: 'Revenue',
        data: processedData.map(item => item.revenue),
        borderColor: '#3b82f6',
        backgroundColor: 'rgba(59, 130, 246, 0.1)',
        borderWidth: 3,
        fill: true,
        tension: 0.4,
        pointRadius: 6,
        pointHoverRadius: 8,
        pointBackgroundColor: '#3b82f6',
        pointBorderColor: '#ffffff',
        pointBorderWidth: 2,
      },
      {
        label: 'Expenses',
        data: processedData.map(item => item.expenses),
        borderColor: '#ef4444',
        backgroundColor: 'rgba(239, 68, 68, 0.1)',
        borderWidth: 3,
        fill: true,
        tension: 0.4,
        pointRadius: 6,
        pointHoverRadius: 8,
        pointBackgroundColor: '#ef4444',
        pointBorderColor: '#ffffff',
        pointBorderWidth: 2,
      },
      {
        label: 'Net Profit',
        data: processedData.map(item => item.profit),
        borderColor: '#10b981',
        backgroundColor: 'rgba(16, 185, 129, 0.1)',
        borderWidth: 3,
        fill: false,
        tension: 0.4,
        pointRadius: 6,
        pointHoverRadius: 8,
        pointBackgroundColor: '#10b981',
        pointBorderColor: '#ffffff',
        pointBorderWidth: 2,
      }
    ],
  };

  const options = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'top',
        labels: {
          usePointStyle: true,
          padding: 20,
          font: {
            size: 12,
            weight: '500'
          }
        }
      },
      tooltip: {
        backgroundColor: '#1f2937',
        titleColor: '#ffffff',
        bodyColor: '#ffffff',
        borderColor: '#374151',
        borderWidth: 1,
        cornerRadius: 8,
        padding: 12,
        displayColors: true,
        callbacks: {
          label: function(context) {
            return `${context.dataset.label}: $${context.parsed.y.toLocaleString()}`;
          }
        }
      }
    },
    scales: {
      x: {
        grid: {
          display: false,
        },
        border: {
          display: false,
        },
        ticks: {
          font: {
            size: 12,
            weight: '500'
          },
          color: '#6b7280'
        }
      },
      y: {
        grid: {
          color: '#f3f4f6',
          drawBorder: false,
        },
        border: {
          display: false,
        },
        ticks: {
          font: {
            size: 12,
            weight: '500'
          },
          color: '#6b7280',
          callback: function(value) {
            return '$' + value.toLocaleString();
          }
        }
      },
    },
    interaction: {
      intersect: false,
      mode: 'index',
    },
    elements: {
      point: {
        hoverBackgroundColor: '#ffffff',
      }
    }
  };

  return (
    <div className="relative">
      <div style={{ height: '400px' }}>
        <Line data={chartData} options={options} />
      </div>
      
      {/* Summary Stats */}
      <div className="grid grid-cols-3 gap-4 mt-6 pt-6 border-t border-gray-200">
        <div className="text-center">
          <div className="text-2xl font-bold text-blue-600">
            ${processedData.reduce((sum, item) => sum + item.revenue, 0).toLocaleString()}
          </div>
          <div className="text-sm text-gray-600">Total Revenue</div>
        </div>
        <div className="text-center">
          <div className="text-2xl font-bold text-red-600">
            ${processedData.reduce((sum, item) => sum + item.expenses, 0).toLocaleString()}
          </div>
          <div className="text-sm text-gray-600">Total Expenses</div>
        </div>
        <div className="text-center">
          <div className="text-2xl font-bold text-green-600">
            ${processedData.reduce((sum, item) => sum + item.profit, 0).toLocaleString()}
          </div>
          <div className="text-sm text-gray-600">Net Profit</div>
        </div>
      </div>
    </div>
  );
};

export default RevenueChart;