CREATE DATABASE "StoreDatabase"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Belarusian_Belarus.1251'
    LC_CTYPE = 'Belarusian_Belarus.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
CREATE TABLE "users" (
	"user_id" serial NOT NULL,
	"username" varchar(245) NOT NULL UNIQUE,
	"password_hash" varchar NOT NULL,
	"email" varchar NOT NULL,
	"enabled" BOOLEAN NOT NULL,
	CONSTRAINT "users_pk" PRIMARY KEY ("user_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "customer" (
	"customer_id" serial NOT NULL,
	"user_id" serial NOT NULL,
	"customer_phone" varchar(255) NOT NULL,
	"customer_status" varchar(50) NOT NULL,
	"firstname" varchar(100) NOT NULL,
	"lastname" varchar(100) NOT NULL,
	"dob" TIMESTAMP NOT NULL,
	"sex" varchar(20) NOT NULL,
	CONSTRAINT "customer_pk" PRIMARY KEY ("customer_id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "adress" (
	"adress_id" serial NOT NULL,
	"customer_id" integer NOT NULL,
	"firstname" varchar(255) NOT NULL,
	"lastname" varchar(255) NOT NULL,
	"adress" varchar(255) NOT NULL,
	"post_index" varchar NOT NULL,
	"city" varchar NOT NULL,
	"region" varchar NOT NULL,
	"phone_number" varchar NOT NULL,
	"email" varchar NOT NULL,
	CONSTRAINT "adress_pk" PRIMARY KEY ("adress_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "designer" (
	"designer_id" serial NOT NULL,
	"designer_name" varchar(255) NOT NULL UNIQUE,
	"user_id" integer  NOT NULL,
	"designer_adress" varchar(255) NOT NULL,
	CONSTRAINT "designer_pk" PRIMARY KEY ("designer_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "item" (
	"item_id" serial NOT NULL,
	"item_name" varchar(255) NOT NULL,
	"publish_date" TIMESTAMP NOT NULL,
	"item_status" varchar(30) NOT NULL,
	"item_designer_id" integer NOT NULL,
	"item_price" DECIMAL NOT NULL,
	"subcategory_id" integer NOT NULL,
	"sex" varchar(20) NOT NULL,
	CONSTRAINT "item_pk" PRIMARY KEY ("item_id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "authority" (
	"authority_id" serial NOT NULL,
	"authority_name" varchar(255) NOT NULL UNIQUE,
	CONSTRAINT "authority_pk" PRIMARY KEY ("authority_id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "role" (
	"role_id" serial NOT NULL,
	"role_name" varchar(50) NOT NULL UNIQUE,
	CONSTRAINT "role_pk" PRIMARY KEY ("role_id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "role_authority" (
	"role_id" integer NOT NULL,
	"authority_id" integer NOT NULL,
	CONSTRAINT "role_authority_pk" PRIMARY KEY ("role_id","authority_id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "users_role" (
	"user_id" integer NOT NULL,
	"role_id" integer NOT NULL,
	CONSTRAINT "users_role_pk" PRIMARY KEY ("user_id","role_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "credit_card" (
	"creditcard_id" serial NOT NULL,
	"customer_id" integer NOT NULL,
	"balance" DECIMAL NOT NULL,
	CONSTRAINT "credit_card_pk" PRIMARY KEY ("creditcard_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "bank_account" (
	"bank_account_id" serial NOT NULL,
	"designer_id" integer NOT NULL,
	"balance" DECIMAL NOT NULL,
	CONSTRAINT "bank_account_pk" PRIMARY KEY ("bank_account_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "image" (
	"image_id" serial NOT NULL,
	"image_name" varchar(255) NOT NULL,
	"image_url" varchar NOT NULL,
	"item_id" integer NOT NULL,
	CONSTRAINT "image_pk" PRIMARY KEY ("image_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "orders" (
	"order_id" serial NOT NULL,
	"order_date" TIMESTAMP NOT NULL,
	"order_cost" decimal NOT NULL,
	"order_status" varchar(255) NOT NULL,
	"customer_id" integer NOT NULL,
	"designer_id" integer NOT NULL,
	CONSTRAINT "orders_pk" PRIMARY KEY ("order_id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "orders_item" (
	"order_id" integer NOT NULL,
	"item_id" integer NOT NULL,
	"offer_price" DECIMAL NOT NULL,
	"size_id" integer NOT NULL,
	"quantity" integer NOT NULL
) WITH (
  OIDS=FALSE
);
CREATE TABLE "size" (
	"size_id" serial NOT NULL,
	"size_name" varchar NOT NULL,
	CONSTRAINT "size_pk" PRIMARY KEY ("size_id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "item_size" (
	"item_id" integer NOT NULL,
	"size_id" integer NOT NULL,
	"quantity" integer NOT NULL,
	CONSTRAINT "category_subcategory_pk" PRIMARY KEY ("item_id","size_id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "subcategory" (
	"subcategory_id" serial NOT NULL,
	"subcategory_name" varchar(80) NOT NULL,
	"category_id" integer NOT NULL,
	CONSTRAINT "subcategory_pk" PRIMARY KEY ("subcategory_id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "category" (
	"category_id" serial NOT NULL,
	"category_name" varchar(80) NOT NULL,
	CONSTRAINT "category_pk" PRIMARY KEY ("category_id")
) WITH (
  OIDS=FALSE
);






ALTER TABLE "customer" ADD CONSTRAINT "customer_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("user_id");
ALTER TABLE "designer" ADD CONSTRAINT "designer_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("user_id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk0" FOREIGN KEY ("item_designer_id") REFERENCES "designer"("designer_id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk1" FOREIGN KEY ("subcategory_id") REFERENCES "subcategory"("subcategory_id");
ALTER TABLE "authority" ADD CONSTRAINT "authority_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("role_id");
ALTER TABLE "users_role" ADD CONSTRAINT "users_role_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("user_id");
ALTER TABLE "users_role" ADD CONSTRAINT "users_role_fk1" FOREIGN KEY ("role_id") REFERENCES "role"("role_id");
ALTER TABLE "credit_card" ADD CONSTRAINT "credit_card_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer"("customer_id");
ALTER TABLE "bank_account" ADD CONSTRAINT "bank_account_fk0" FOREIGN KEY ("designer_id") REFERENCES "designer"("designer_id");
ALTER TABLE "image" ADD CONSTRAINT "image_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("item_id");
ALTER TABLE "orders" ADD CONSTRAINT "orders_fk0" FOREIGN KEY ("designer_id") REFERENCES "designer"("designer_id");
ALTER TABLE "orders" ADD CONSTRAINT "orders_fk1" FOREIGN KEY ("customer_id") REFERENCES "customer"("customer_id");
ALTER TABLE "orders_item" ADD CONSTRAINT "orders_item_fk0" FOREIGN KEY ("order_id") REFERENCES "orders"("order_id");
ALTER TABLE "orders_item" ADD CONSTRAINT "orders_item_fk1" FOREIGN KEY ("item_id") REFERENCES "item"("item_id");
ALTER TABLE "orders_item" ADD CONSTRAINT "orders_item_fk2" FOREIGN KEY ("size_id") REFERENCES "size"("size_id");
ALTER TABLE "category" ADD CONSTRAINT "category_fk0" FOREIGN KEY ("category_id") REFERENCES "category"("category_id");
ALTER TABLE "adress" ADD CONSTRAINT "adress_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer"("customer_id");
ALTER TABLE "item_size" ADD CONSTRAINT "item_size_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("item_id");
ALTER TABLE "item_size" ADD CONSTRAINT "item_size_fk1" FOREIGN KEY ("size_id") REFERENCES "size"("size_id");
ALTER TABLE "role_authority" ADD CONSTRAINT "role_authority_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("role_id");
ALTER TABLE "role_authority" ADD CONSTRAINT "role_authority_fk1" FOREIGN KEY ("authority_id") REFERENCES "authority"("authority_id");
