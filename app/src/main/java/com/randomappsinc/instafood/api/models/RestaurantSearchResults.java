package com.randomappsinc.instafood.api.models;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.randomappsinc.instafood.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSearchResults {

    @SerializedName("businesses")
    @Expose
    private List<Business> businesses;

    public class Business {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("image_url")
        @Expose
        private String imageUrl;

        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("rating")
        @Expose
        private double rating;

        @SerializedName("review_count")
        @Expose
        private int reviewCount;

        @SerializedName("display_phone")
        @Expose
        private String phoneNumber;

        @SerializedName("price")
        @Expose
        private String price;

        @SerializedName("coordinates")
        @Expose
        private Coordinates coordinates;

        class Coordinates {
            @SerializedName("latitude")
            @Expose
            private double latitude;

            @SerializedName("longitude")
            @Expose
            private double longitude;

            double getLatitude() {
                return latitude;
            }

            double getLongitude() {
                return longitude;
            }
        }

        @SerializedName("location")
        @Expose
        private Location location;

        class Location {
            @SerializedName("address1")
            @Expose
            private String address1;

            @SerializedName("city")
            @Expose
            private String city;

            String getCity() {
                return city;
            }

            String getAddress() {
                StringBuilder address = new StringBuilder();
                if (!TextUtils.isEmpty(address1)) {
                    address.append(address1).append(", ");
                }
                address.append(city);
                return address.toString();
            }
        }

        // Distance in meters from the restaurant location
        @SerializedName("distance")
        @Expose
        private double distance;

        @SerializedName("categories")
        @Expose
        private List<Category> categories;

        class Category {
            @SerializedName("title")
            @Expose
            private String title;

            String getTitle() {
                return title;
            }
        }

        Restaurant toRestaurant() {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);
            restaurant.setName(name);
            restaurant.setImageUrl(imageUrl);
            restaurant.setUrl(url);
            restaurant.setRating(rating);
            restaurant.setReviewCount(reviewCount);
            restaurant.setPhoneNumber(phoneNumber);
            restaurant.setPrice(price);
            restaurant.setAddress(location.getAddress());
            restaurant.setLatitude(coordinates.getLatitude());
            restaurant.setLongitude(coordinates.getLongitude());
            restaurant.setDistance(distance);
            ArrayList<String> restaurantCategories = new ArrayList<>();
            for (Category category : categories) {
                restaurantCategories.add(category.getTitle());
            }
            restaurant.setCategories(restaurantCategories);
            return restaurant;
        }
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Business business : businesses) {
            restaurants.add(business.toRestaurant());
        }
        return restaurants;
    }
}
