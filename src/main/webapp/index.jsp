<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #e0e0e0;
            font-family: Arial, sans-serif;
        }
        .contact-form {
            background-color: #f9f9f9;
            padding: 20px;
            width: 35%;
            border-radius: 5px;
        }
        .contact-form h2 {
            margin-top: 0;
            color: #333;
        }
        .contact-form p {
            margin: 0;
            padding-bottom: 10px;
            font-size: 0.9em;
            color: #666;
        }
        .contact-form label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .contact-form input[type="text"],
        .contact-form input[type="email"],
        .contact-form textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1em;
        }
        .contact-form textarea {
            height: 80px;
        }
        .contact-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #888;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 1em;
            cursor: pointer;
        }
        .contact-form input[type="submit"]:hover {
            background-color: #666;
        }
        .contact-form .required {
            color: red;
        }
        a{
            width:20%;
            justify-content:center;
        }
    </style>
</head>
<body>
<div class="contact-form">
    <h2>Contact Us</h2>
    <p>Please fill this form in a decent manner</p>
    <form action="contactuss" method="post">
        <label for="name">Full Name <span class="required">*</span></label>
        <input type="text" id="name" name="name" required>

        <label for="email">E-mail <span class="required">*</span></label>
        <input type="email" id="email" name="email" required placeholder="example@example.com">

        <label for="message">Message <span class="required">*</span></label>
        <textarea id="message" name="message" required></textarea>

        <input type="submit" value="SUBMIT">
    </form>
    <a href="login.jsp">Admin Login</a>
</div>
</body>
</html>
