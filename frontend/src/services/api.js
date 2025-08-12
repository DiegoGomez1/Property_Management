import axios from 'axios';

// Create axios instance with base configuration
const api = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  }
});

// Request interceptor
api.interceptors.request.use(
  (config) => {
    // Add auth token if available
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor
api.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    if (error.response?.status === 401) {
      // Handle unauthorized
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

// Property/Building Services
export const propertyService = {
  // Get all buildings
  getAllBuildings: () => api.get('/buildings'),
  
  // Get building by ID
  getBuildingById: (id) => api.get(`/buildings/${id}`),
  
  // Create new building
  createBuilding: (building) => api.post('/buildings', building),
  
  // Update building
  updateBuilding: (id, building) => api.put(`/buildings/${id}`, building),
  
  // Delete building
  deleteBuilding: (id) => api.delete(`/buildings/${id}`),
  
  // Get building analytics
  getBuildingAnalytics: (buildingId, year) => 
    api.get(`/api/analytics/buildings/${buildingId}/yearly/${year}`),
  
  // Get buildings with units
  getBuildingWithUnits: (id) => api.get(`/buildings/${id}?include=units`),
};

// Unit Services
export const unitService = {
  // Get all units
  getAllUnits: () => api.get('/units'),
  
  // Get unit by ID
  getUnitById: (id) => api.get(`/units/${id}`),
  
  // Create new unit
  createUnit: (unit) => api.post('/units', unit),
  
  // Update unit
  updateUnit: (id, unit) => api.put(`/units/${id}`, unit),
  
  // Delete unit
  deleteUnit: (id) => api.delete(`/units/${id}`),
  
  // Get units by building
  getUnitsByBuilding: (buildingId) => api.get(`/units?buildingId=${buildingId}`),
};

// Lease Services
export const leaseService = {
  // Get all leases
  getAllLeases: () => api.get('/leases'),
  
  // Get lease by ID
  getLeaseById: (id) => api.get(`/leases/${id}`),
  
  // Create new lease
  createLease: (lease) => api.post('/leases', lease),
  
  // Update lease
  updateLease: (id, lease) => api.put(`/leases/${id}`, lease),
  
  // Delete lease
  deleteLease: (id) => api.delete(`/leases/${id}`),
  
  // Get leases by tenant
  getLeasesByTenant: (tenantId) => api.get(`/leases?tenantId=${tenantId}`),
  
  // Get leases by unit
  getLeasesByUnit: (unitId) => api.get(`/leases?unitId=${unitId}`),
};

// Tenant Services
export const tenantService = {
  // Get all tenants
  getAllTenants: () => api.get('/tenants'),
  
  // Get tenant by ID
  getTenantById: (id) => api.get(`/tenants/${id}`),
  
  // Create new tenant
  createTenant: (tenant) => api.post('/tenants', tenant),
  
  // Update tenant
  updateTenant: (id, tenant) => api.put(`/tenants/${id}`, tenant),
  
  // Delete tenant
  deleteTenant: (id) => api.delete(`/tenants/${id}`),
};

// User Services
export const userService = {
  // Get all users
  getAllUsers: () => api.get('/api/users'),
  
  // Get user by ID
  getUserById: (id) => api.get(`/api/users/${id}`),
  
  // Create new user
  createUser: (user) => api.post('/api/users/register', user),
  
  // Update user
  updateUser: (id, user) => api.put(`/api/users/${id}`, user),
  
  // Delete user
  deleteUser: (id) => api.delete(`/api/users/${id}`),
  
  // Check if email exists
  checkEmailExists: (email) => api.get(`/api/users/check-email?email=${email}`),
  
  // Get users by role
  getUsersByRole: (role) => api.get(`/api/users/role/${role}`),
};

// Payment Services
export const paymentService = {
  // Get payments by building and date range
  getPaymentsByBuilding: (buildingId, startDate, endDate, status) => 
    api.get(`/payments/building/${buildingId}`, {
      params: { startDate, endDate, status }
    }),
  
  // Get monthly revenue
  getMonthlyRevenue: (buildingId, year, type) =>
    api.get(`/payments/monthly-revenue`, {
      params: { buildingId, year, type }
    }),
};

// Expense Services
export const expenseService = {
  // Get expenses by building
  getExpensesByBuilding: (buildingId) => api.get(`/expenses/building/${buildingId}`),
  
  // Get expenses by date range
  getExpensesByDateRange: (buildingId, startDate, endDate) =>
    api.get(`/expenses/building/${buildingId}/range`, {
      params: { startDate, endDate }
    }),
  
  // Get expenses by category
  getExpensesByCategory: (buildingId, year) =>
    api.get(`/expenses/building/${buildingId}/category`, {
      params: { year }
    }),
  
  // Create expense
  createExpense: (expense) => api.post('/expenses', expense),
  
  // Update expense
  updateExpense: (id, expense) => api.put(`/expenses/${id}`, expense),
  
  // Delete expense
  deleteExpense: (id) => api.delete(`/expenses/${id}`),
};

// Maintenance Services
export const maintenanceService = {
  // Get maintenance requests by building
  getMaintenanceByBuilding: (buildingId) => 
    api.get(`/maintenance/building/${buildingId}`),
  
  // Create maintenance request
  createMaintenanceRequest: (request) => api.post('/maintenance', request),
  
  // Update maintenance request
  updateMaintenanceRequest: (id, request) => api.put(`/maintenance/${id}`, request),
  
  // Delete maintenance request
  deleteMaintenanceRequest: (id) => api.delete(`/maintenance/${id}`),
};

// Analytics Services
export const analyticsService = {
  // Get building performance
  getBuildingPerformance: (buildingId, year) =>
    api.get(`/api/analytics/buildings/${buildingId}/yearly/${year}`),
  
  // Get portfolio overview
  getPortfolioOverview: (ownerId) =>
    api.get(`/api/analytics/portfolio/${ownerId}`),
  
  // Get financial metrics
  getFinancialMetrics: (buildingId, startDate, endDate) =>
    api.get(`/api/analytics/financial/${buildingId}`, {
      params: { startDate, endDate }
    }),
};

export default api;