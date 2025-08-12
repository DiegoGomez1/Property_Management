// Demo data for development and testing
export const demoBuildings = [
  {
    id: 1,
    name: "Sunset Apartments",
    address: "123 Main St",
    city: "Los Angeles",
    state: "CA",
    zipcode: "90210",
    buildingType: "RESIDENTIAL",
    totalUnits: 24,
    totalFloors: 3,
    yearBuilt: 2020,
    occupancyRate: 87.5,
    propertyValue: 2500000,
    monthlyMaintenanceFee: 1500,
    status: "ACTIVE",
    units: [
      { id: 1, unitNumber: "101", rentAmount: 2800, status: "OCCUPIED" },
      { id: 2, unitNumber: "102", rentAmount: 2600, status: "OCCUPIED" },
      { id: 3, unitNumber: "103", rentAmount: 2800, status: "VACANT" },
      { id: 4, unitNumber: "201", rentAmount: 3200, status: "OCCUPIED" }
    ]
  },
  {
    id: 2,
    name: "Downtown Business Center",
    address: "456 Business Blvd",
    city: "San Francisco",
    state: "CA",
    zipcode: "94102",
    buildingType: "COMMERCIAL",
    totalUnits: 12,
    totalFloors: 8,
    yearBuilt: 2018,
    occupancyRate: 91.7,
    propertyValue: 4200000,
    monthlyMaintenanceFee: 2800,
    status: "ACTIVE",
    units: [
      { id: 5, unitNumber: "Suite 101", rentAmount: 8500, status: "OCCUPIED" },
      { id: 6, unitNumber: "Suite 102", rentAmount: 7200, status: "OCCUPIED" },
      { id: 7, unitNumber: "Suite 201", rentAmount: 9800, status: "VACANT" }
    ]
  },
  {
    id: 3,
    name: "Green Valley Condos",
    address: "789 Valley View Dr",
    city: "Austin",
    state: "TX",
    zipcode: "78701",
    buildingType: "RESIDENTIAL",
    totalUnits: 36,
    totalFloors: 4,
    yearBuilt: 2022,
    occupancyRate: 94.4,
    propertyValue: 3800000,
    monthlyMaintenanceFee: 2200,
    status: "ACTIVE",
    units: [
      { id: 8, unitNumber: "A101", rentAmount: 1800, status: "OCCUPIED" },
      { id: 9, unitNumber: "A102", rentAmount: 1950, status: "OCCUPIED" },
      { id: 10, unitNumber: "B201", rentAmount: 2100, status: "OCCUPIED" }
    ]
  }
];

export const demoAnalytics = {
  buildingId: 1,
  buildingName: "Sunset Apartments",
  year: 2024,
  totalRevenue: 672000,
  totalExpenses: 145000,
  netOperatingIncome: 527000,
  roi: 21.08,
  averageOccupancyRate: 87.5,
  monthlyRevenues: [
    { month: 1, monthName: "January", revenue: 52000, expenses: 12000, netIncome: 40000, occupancyRate: 85.0 },
    { month: 2, monthName: "February", revenue: 54000, expenses: 11500, netIncome: 42500, occupancyRate: 87.5 },
    { month: 3, monthName: "March", revenue: 56000, expenses: 13000, netIncome: 43000, occupancyRate: 90.0 },
    { month: 4, monthName: "April", revenue: 58000, expenses: 12500, netIncome: 45500, occupancyRate: 92.5 },
    { month: 5, monthName: "May", revenue: 57000, expenses: 12800, netIncome: 44200, occupancyRate: 89.0 },
    { month: 6, monthName: "June", revenue: 59000, expenses: 11800, netIncome: 47200, occupancyRate: 95.0 },
    { month: 7, monthName: "July", revenue: 58500, expenses: 12200, netIncome: 46300, occupancyRate: 93.0 },
    { month: 8, monthName: "August", revenue: 57500, expenses: 12000, netIncome: 45500, occupancyRate: 91.0 },
    { month: 9, monthName: "September", revenue: 56500, expenses: 12500, netIncome: 44000, occupancyRate: 88.0 },
    { month: 10, monthName: "October", revenue: 55000, expenses: 13500, netIncome: 41500, occupancyRate: 85.0 },
    { month: 11, monthName: "November", revenue: 54500, expenses: 12200, netIncome: 42300, occupancyRate: 87.0 },
    { month: 12, monthName: "December", revenue: 55000, expenses: 12000, netIncome: 43000, occupancyRate: 88.0 }
  ]
};

export const demoMetrics = {
  totalProperties: 3,
  totalUnits: 72,
  averageOccupancy: 91.2,
  monthlyRevenue: 125000,
  yearlyRevenue: 1500000,
  totalExpenses: 285000,
  netIncome: 1215000
};