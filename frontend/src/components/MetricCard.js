import React from 'react';
import { TrendingUp, TrendingDown } from 'lucide-react';

const MetricCard = ({ 
  title, 
  value, 
  icon: Icon, 
  change, 
  changeType = 'neutral', 
  subtitle,
  loading = false 
}) => {
  const getChangeColor = () => {
    switch (changeType) {
      case 'positive': return 'text-green-600 bg-green-50';
      case 'negative': return 'text-red-600 bg-red-50';
      default: return 'text-gray-600 bg-gray-50';
    }
  };

  const getChangeIcon = () => {
    switch (changeType) {
      case 'positive': return TrendingUp;
      case 'negative': return TrendingDown;
      default: return null;
    }
  };

  const ChangeIcon = getChangeIcon();

  if (loading) {
    return (
      <div className="card">
        <div className="animate-pulse">
          <div className="flex items-center justify-between mb-4">
            <div className="h-4 bg-gray-200 rounded w-24"></div>
            <div className="h-8 w-8 bg-gray-200 rounded-lg"></div>
          </div>
          <div className="h-8 bg-gray-200 rounded w-20 mb-2"></div>
          <div className="h-4 bg-gray-200 rounded w-16"></div>
        </div>
      </div>
    );
  }

  return (
    <div className="card metric-card hover:shadow-lg transition-all duration-200">
      {/* Header */}
      <div className="flex items-center justify-between mb-4">
        <h3 className="text-sm font-medium text-gray-600">{title}</h3>
        <div className="p-2 bg-blue-100 rounded-lg">
          <Icon className="h-5 w-5 text-blue-600" />
        </div>
      </div>

      {/* Value */}
      <div className="mb-2">
        <div className="text-2xl font-bold text-gray-900">{value}</div>
        {subtitle && (
          <div className="text-sm text-gray-600">{subtitle}</div>
        )}
      </div>

      {/* Change Indicator */}
      {change && (
        <div className="flex items-center gap-1">
          {ChangeIcon && (
            <ChangeIcon className="h-4 w-4" />
          )}
          <span className={`text-sm font-medium px-2 py-1 rounded-full ${getChangeColor()}`}>
            {change}
          </span>
          <span className="text-xs text-gray-500 ml-1">vs last month</span>
        </div>
      )}
    </div>
  );
};

export default MetricCard;