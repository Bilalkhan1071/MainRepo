import pygame
import random

# Initialize Pygame
pygame.init()

# Set up the display
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Snake Game")

# Set up the colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
GREEN = (0, 255, 0)

# Set up the font
font = pygame.font.SysFont(None, 30)

# Set up the clock
clock = pygame.time.Clock()

# Set up the Snake
SNAKE_SIZE = 20
SNAKE_SPEED = 20
snake_x = SCREEN_WIDTH // 2
snake_y = SCREEN_HEIGHT // 2
snake_dx = SNAKE_SPEED
snake_dy = 0
snake_length = 1
snake_body = [(snake_x, snake_y)]

# Set up the Food
FOOD_SIZE = 20
food_x = random.randrange(0, SCREEN_WIDTH - FOOD_SIZE, FOOD_SIZE)
food_y = random.randrange(0, SCREEN_HEIGHT - FOOD_SIZE, FOOD_SIZE)
food_rect = pygame.Rect(food_x, food_y, FOOD_SIZE, FOOD_SIZE)

# Set up the score
score = 0
score_text = font.render("Score: " + str(score), True, WHITE)

# Set up the pause button
PAUSE_BUTTON_WIDTH = 80
PAUSE_BUTTON_HEIGHT = 40
pause_button_rect = pygame.Rect(SCREEN_WIDTH - PAUSE_BUTTON_WIDTH - 10, 10, PAUSE_BUTTON_WIDTH, PAUSE_BUTTON_HEIGHT)

# Set up the game loop
game_over = False
paused = False

while not game_over:

    # Event handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            game_over = True
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP and snake_dy == 0:
                snake_dx = 0
                snake_dy = -SNAKE_SPEED
            elif event.key == pygame.K_DOWN and snake_dy == 0:
                snake_dx = 0
                snake_dy = SNAKE_SPEED
            elif event.key == pygame.K_LEFT and snake_dx == 0:
                snake_dx = -SNAKE_SPEED
                snake_dy = 0
            elif event.key == pygame.K_RIGHT and snake_dx == 0:
                snake_dx = SNAKE_SPEED
                snake_dy = 0
            elif event.key == pygame.K_SPACE:
                paused = not paused

    # Update the Snake
    if not paused:
        snake_x += snake_dx
        snake_y += snake_dy

        # Check if Snake hits the wall
        if snake_x < 0 or snake_x > SCREEN_WIDTH - SNAKE_SIZE or snake_y < 0 or snake_y > SCREEN_HEIGHT - SNAKE_SIZE:
            snake_length = 1
            snake_body = [(SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2)]
            score = 0
            score_text = font.render("Score: " + str(score), True, WHITE)

        # Check if Snake hits itself
        for i in range(1, len(snake_body)):
            if snake_x == snake_body[i][0] and snake_y == snake_body[i][1]:
                snake_length = 1
                snake_body = [(SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2)]
                score = 0
                score_text = font.render("Score:" + str(score), True, WHITE)

        # Check if Snake eats Food
        if snake_x == food_x and snake_y == food_y:
            food_x = random.randrange(0, SCREEN_WIDTH - FOOD_SIZE, FOOD_SIZE)
            food_y = random.randrange(0, SCREEN_HEIGHT - FOOD_SIZE, FOOD_SIZE)
            food_rect = pygame.Rect(food_x, food_y, FOOD_SIZE, FOOD_SIZE)
            snake_length += 1
            score += 10
            score_text = font.render("Score: " + str(score), True, WHITE)

        # Update the Snake body
        snake_body.insert(0, (snake_x, snake_y))
        if len(snake_body) > snake_length:
            snake_body.pop()

    # Draw the screen
    screen.fill(BLACK)
    pygame.draw.rect(screen, GREEN, food_rect)
    pygame.draw.rect(screen, WHITE, pause_button_rect)

    # Draw the Snake
    for i in range(len(snake_body)):
        pygame.draw.rect(screen, WHITE, pygame.Rect(snake_body[i][0], snake_body[i][1], SNAKE_SIZE, SNAKE_SIZE))

    # Draw the score
    screen.blit(score_text, (10, 10))

    # Draw the pause button text
    if paused:
        pause_text = font.render("Resume", True, WHITE)
    else:
        pause_text = font.render("Pause", True, WHITE)
    pause_text_rect = pause_text.get_rect(center=pause_button_rect.center)
    screen.blit(pause_text, pause_text_rect)

    # Update the display
    pygame.display.flip()

    # Set the framerate
    clock.tick(10)

# Quit Pygame
pygame.quit()


