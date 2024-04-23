Create TABLE if not exists `customer`(
	`customer_id` int Auto_increment primary key,
	`name` varchar(100) Not Null,
	`mobile_number` varchar(20) Not Null,
	`email` varchar(100) Not Null,
	`created_at` date Not Null,
	`created_by` varchar(20) Not Null,
	`updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

Create TABLE if not exists `accounts`(
	`account_no` int Auto_increment primary key,
	`customer_id` int Not Null,
	`account_type` varchar(20) Not Null,
	`branch_address` varchar(100) Not Null,
	`created_at` date Not Null,
	`created_by` varchar(20) Not Null,
	`updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);