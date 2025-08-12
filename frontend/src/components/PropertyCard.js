import React from 'react';
import { MapPin, Home, Users, DollarSign, TrendingUp, MoreVertical } from 'lucide-react';

const PropertyCard = ({ building }) => {
  const {
    id,
    name,
    address,
    city,
    state,
    totalUnits,
    buildingType,
    occupancyRate = 0,
    monthlyMaintenanceFee,
    propertyValue,
    units = []
  } = building;

  const occupiedUnits = units.filter(unit => unit.status === 'OCCUPIED').length;
  const vacantUnits = totalUnits - occupiedUnits;
  const totalRent = units.reduce((sum, unit) => sum + (unit.rentAmount || 0), 0);

  const getOccupancyColor = (rate) => {
    if (rate >= 90) return 'text-green-600 bg-green-50';
    if (rate >= 70) return 'text-blue-600 bg-blue-50';
    if (rate >= 50) return 'text-yellow-600 bg-yellow-50';
    return 'text-red-600 bg-red-50';
  };

  const getBuildingTypeDisplay = (type) => {
    switch (type) {
      case 'RESIDENTIAL': return 'Residential';
      case 'COMMERCIAL': return 'Commercial';
      case 'MIXED_USE': return 'Mixed Use';
      case 'INDUSTRIAL': return 'Industrial';
      default: return type;
    }
  };

  return (
    <div className="card hover:shadow-xl transition-all duration-300 group">
      {/* Header */}
      <div className="flex items-start justify-between mb-4">
        <div className="flex-1">
          <h3 className="text-lg font-semibold text-gray-900 group-hover:text-blue-600 transition-colors">
            {name}
          </h3>
          <div className="flex items-center text-gray-600 mt-1">
            <MapPin className="h-4 w-4 mr-1" />
            <span className="text-sm">{address}, {city}, {state}</span>
          </div>
        </div>
        <button className="p-2 hover:bg-gray-100 rounded-lg transition-colors">
          <MoreVertical className="h-4 w-4 text-gray-400" />
        </button>
      </div>

      {/* Building Type Badge */}
      <div className="mb-4">
        <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800">
          {getBuildingTypeDisplay(buildingType)}
        </span>
      </div>

      {/* Key Metrics */}
      <div className="grid grid-cols-2 gap-4 mb-4">
        <div className="text-center p-3 bg-gray-50 rounded-lg">
          <Home className="h-5 w-5 text-gray-600 mx-auto mb-1" />
          <div className="text-xl font-bold text-gray-900">{totalUnits}</div>
          <div className="text-xs text-gray-600">Total Units</div>
        </div>
        <div className="text-center p-3 bg-gray-50 rounded-lg">
          <Users className="h-5 w-5 text-gray-600 mx-auto mb-1" />
          <div className="text-xl font-bold text-gray-900">{occupiedUnits}</div>
          <div className="text-xs text-gray-600">Occupied</div>
        </div>
      </div>

      {/* Occupancy Rate */}
      <div className="mb-4">
        <div className="flex items-center justify-between mb-2">
          <span className="text-sm font-medium text-gray-700">Occupancy Rate</span>
          <span className={`text-sm font-semibold px-2 py-1 rounded-full ${getOccupancyColor(occupancyRate)}`}>
            {occupancyRate.toFixed(1)}%
          </span>
        </div>
        <div className="progress-bar">
          <div 
            className="progress-fill" 
            style={{ width: `${Math.min(occupancyRate, 100)}%` }}
          ></div>
        </div>
      </div>

      {/* Financial Info */}
      <div className="border-t border-gray-200 pt-4">
        <div className="flex items-center justify-between mb-2">
          <span className="text-sm text-gray-600">Monthly Potential</span>
          <span className="text-lg font-bold text-gray-900">
            ${totalRent.toLocaleString()}
          </span>
        </div>
        
        {propertyValue && (
          <div className="flex items-center justify-between mb-2">
            <span className="text-sm text-gray-600">Property Value</span>
            <span className="text-sm font-medium text-gray-900">
              ${propertyValue.toLocaleString()}
            </span>
          </div>
        )}

        {monthlyMaintenanceFee && (
          <div className="flex items-center justify-between">
            <span className="text-sm text-gray-600">Monthly Fees</span>
            <span className="text-sm font-medium text-gray-900">
              ${monthlyMaintenanceFee.toLocaleString()}
            </span>
          </div>
        )}
      </div>

      {/* Vacant Units Alert */}
      {vacantUnits > 0 && (
        <div className="mt-4 p-3 bg-yellow-50 border border-yellow-200 rounded-lg">
          <div className="flex items-center">
            <div className="flex-shrink-0">
              <Home className="h-5 w-5 text-yellow-600" />
            </div>
            <div className="ml-3">
              <p className="text-sm font-medium text-yellow-800">
                {vacantUnits} vacant unit{vacantUnits > 1 ? 's' : ''}
              </p>
              <p className="text-xs text-yellow-600">
                Potential monthly loss: ${(vacantUnits * (totalRent / totalUnits)).toLocaleString()}
              </p>
            </div>
          </div>
        </div>
      )}

      {/* Action Buttons */}
      <div className="mt-6 flex gap-2">
        <button className="btn btn-primary flex-1">
          View Details
        </button>
        <button className="btn btn-secondary">
          <TrendingUp className="h-4 w-4" />
        </button>
      </div>
    </div>
  );
};

export default PropertyCard;