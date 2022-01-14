# Online Store API <!-- omit in toc -->

## Info <!-- omit in toc -->
A maven and downloadable artifact exists under the packages tab. Test reports exists as well for the few tests that exists under the actions tab, a new report exists for each push since it was implemented (don't mind the commit messages).
## Table of Contents <!-- omit in toc -->
- [Requirements](#requirements)
- [Setup](#setup)
- [Endpoints](#endpoints)
	- [__Users__](#users)
		- [Get all users](#get-all-users)
		- [Get a user by ID](#get-a-user-by-id)
		- [Create a user](#create-a-user)
		- [Delete a user](#delete-a-user)
		- [Update users password](#update-users-password)
		- [Update users name](#update-users-name)
		- [Update users address](#update-users-address)
	- [__Stores__](#stores)
		- [Get all stores](#get-all-stores)
		- [Get a store by ID](#get-a-store-by-id)
		- [Create a store](#create-a-store)
		- [Delete a store](#delete-a-store)
	- [__Products__](#products)
		- [Get all products](#get-all-products)
		- [Get a product by ID](#get-a-product-by-id)
		- [Create a product](#create-a-product)
		- [Delete a product](#delete-a-product)
	- [__Manufacturers__](#manufacturers)
		- [Get all manufacturers](#get-all-manufacturers)
		- [Get a manufacturer by ID](#get-a-manufacturer-by-id)
		- [Create a manufacturer](#create-a-manufacturer)
		- [Delete a manufacturer](#delete-a-manufacturer)
	- [__Carts__](#carts)
		- [Get all carts](#get-all-carts)
		- [Get a cart by user ID](#get-a-cart-by-user-id)
		- [Add a product to a cart](#add-a-product-to-a-cart)
		- [Remove a product from a cart](#remove-a-product-from-a-cart)
		- [Clear a cart](#clear-a-cart)
	- [__Security__](#security)
		- [Signin](#signin)
		- [Signup](#signup)
		- [Add admin role to a user](#add-admin-role-to-a-user)
		- [Remove admin role from a user](#remove-admin-role-from-a-user)

## Requirements
* Docker
* Java
## Setup
Clone the repository and navigate into its directory
```sh
git clone git@github.com:tntenterprises/OnlineStore.git
cd OnlineStore
```

Start artemis
```sh
docker pull vromero/activemq-artemis
docker run -it --rm -p 61616:61616 -p 8161:8161 vromero/activemq-artemis
```

Start the application
```sh
./mvnw clean package
java -jar target/onlinestore-1.0.0.jar -DskipTests
```

## Endpoints
### __Users__
#### Get all users
`
GET - http://localhost:80/users
`
#### Get a user by ID
`
GET - http://localhost:80/users/{id}
`

#### Create a user
`
POST - http://localhost:80/users
`

Body
```json
{
	"email": "admin@tnt.com",
	"name": "Admin User",
	"password": "tntenterprises",
	"address": "Nowhere to be found"
}
```

#### Delete a user
`
DELETE - http://localhost:80/users/{id}
`

#### Update users password
`
PATCH - http://localhost:80/users/changepassword/{id}
`

Body
```json
{
	"password": "newPassword"
}
```

#### Update users name
`
PATCH - http://localhost:80/users/changename/{id}
`

Body
```json
{
	"name": "New Name"
}
```

#### Update users address
`
PATCH - http://localhost:80/users/changeaddress/{id}
`

Body
```json
{
	"address": "New Address 123"
}
```
### __Stores__
#### Get all stores
`
GET - http://localhost:80/stores
`
#### Get a store by ID
`
GET - http://localhost:80/stores/{id}
`

#### Create a store
`
POST - http://localhost:80/stores
`

Body
```json
{
	"city": "Göteborg"
}
```

#### Delete a store
`
DELETE - http://localhost:80/stores/{id}
`
### __Products__
#### Get all products
`
GET - http://localhost:80/products
`
#### Get a product by ID
`
GET - http://localhost:80/products/{id}
`

#### Create a product
`
POST - http://localhost:80/products/{storeIds}
`

Note: storeIds is optional (a list of all stores that (should) carry said product)

Body
```json
{
	"price": 384000,
	"name": "Bill Wyman's 1969 Fender Mustang Bass"
}
```

#### Delete a product
`
DELETE - http://localhost:80/products/{id}
`
### __Manufacturers__
#### Get all manufacturers
`
GET - http://localhost:80/manufacturers
`
#### Get a manufacturer by ID
`
GET - http://localhost:80/manufacturers/{id}
`

#### Create a manufacturer
`
POST - http://localhost:80/manufacturer/{storeIds}
`

Body
```json
{
	"name": "Anna's Rustic Kitchens"
}
```

#### Delete a manufacturer
`
DELETE - http://localhost:80/manufacturer/{id}
`
### __Carts__
#### Get all carts
`
GET - http://localhost:80/carts
`
#### Get a cart by user ID
`
GET - http://localhost:80/carts/{userId}
`

#### Add a product to a cart
`
PUT - http://localhost:80/carts/addproduct/{userId}
`

Body
```json
{
	"price": 384000,
	"name": "Bill Wyman's 1969 Fender Mustang Bass"
}
```

#### Remove a product from a cart
`
PUT - http://localhost:80/carts/removeproduct/{userId}/{productId}
`

#### Clear a cart
`
PUT - http://localhost:80/carts/clear/{userId}
`

### __Security__
#### Signin
`
POST - http://localhost:80/signin
`

Body
```json
{
	"email": "admin@tnt.com",
	"password": "tntenterprises"
}
```

#### Signup
`
POST - http://localhost:80/signup
`

Body
```json
{
	"email": "admin@tnt.com",
	"password": "tntenterprises",
	"name": "Admin User",
	"address": "Nowhere to be found"
}
```

#### Add admin role to a user
`
PUT - http://localhost:80/admin/makeadmin/{id}
`

#### Remove admin role from a user
`
PUT - http://localhost:80/admin/removeadmin/{id}
`