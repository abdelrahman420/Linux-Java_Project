import sys
import re

def parse_lircd_conf(filename):
    """Parse the LIRC configuration file to extract raw IR codes for keys."""
    keys = {}
    key_sequences = {}

    with open(filename, 'r') as file:
        key_name = None
        for line in file:
            line = line.strip()
            
            # Find the raw code entries
            if line.startswith("begin codes"):
                continue
            elif line.startswith("end codes"):
                break
            elif line.startswith("KEY_"):
                parts = line.split()
                key_name = parts[0]
                key_sequence = parts[1]
                keys[key_name] = key_sequence

    # Decode raw codes into their binary form
    for key_name, hex_code in keys.items():
        binary_sequence = ''.join(format(int(c, 16), '04b') for c in hex_code[2:])
        key_sequences[key_name] = binary_sequence

    return key_sequences

def parse_mode2_output(input_stream):
    """Parse mode2 output to get raw pulse and space timings."""
    pulse_pattern = re.compile(r"pulse (\d+)")
    space_pattern = re.compile(r"space (\d+)")
    sequence = []

    for line in input_stream:
        line = line.strip()

        # Match pulse and space outputs
        pulse_match = pulse_pattern.match(line)
        if pulse_match:
            sequence.append(int(pulse_match.group(1)))
            continue

        space_match = space_pattern.match(line)
        if space_match:
            sequence.append(int(space_match.group(1)))
            continue

    return sequence

def decode_sequence(sequence, key_sequences):
    """Decode the sequence into a key name using the key sequences."""
    for key_name, key_sequence in key_sequences.items():
        # Convert mode2 sequence to binary form
        binary_sequence = ''.join(format(x, '04b') for x in sequence)
        if binary_sequence.startswith(key_sequence):
            return key_name
    return "Unknown Key"

def main():
    if len(sys.argv) < 2:
        print("Usage: python parse_mode2_lirc.py <lircd.conf>")
        sys.exit(1)

    # Parse the configuration file
    config_file = sys.argv[1]
    key_sequences = parse_lircd_conf(config_file)

    # Parse mode2 output from stdin
    parsed_sequence = parse_mode2_output(sys.stdin)

    # Decode the sequence to a key name
    key_name = decode_sequence(parsed_sequence, key_sequences)

    print(f"Detected Key: {key_name}")

if __name__ == "__main__":
    main()

